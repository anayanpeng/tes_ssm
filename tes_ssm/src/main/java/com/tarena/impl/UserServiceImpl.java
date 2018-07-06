package com.tarena.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tarena.dao.RoleMapper;
import com.tarena.dao.UserMapper;
import com.tarena.entity.User;
import com.tarena.entity.UserRole;
import com.tarena.service.UserService;
import com.tarena.util.CommonValue;
import com.tarena.util.PageUtil;
import com.tarena.util.PrintWriterUtil;
import com.tarena.util.UUIDUtil;
import com.tarena.util.UploadUtil;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	@Resource(name = "pageUtil")
	private PageUtil pageUtil;

	@Override
	public Result login(String loginName, String password) {
		Result result = new Result();
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		String userId = this.userMapper.login(user);
		if (userId != null) {
			result.setStatus(1);
			result.setMessage("登录成功!");
		} else {
			result.setStatus(0);
			result.setMessage("登录失败!");
		}
		return result;
	}

	@Override
	public Result findUsersByPage(Page page) {
		Result result=new Result();
		if("all".equals(page.getRoleType())){
			//查询所有角色
			page.setUserKeyword("undefinded".equals(page.getUserKeyword()) ? "%%" :"%"+page.getUserKeyword()+"%");
			page.setPageSize(pageUtil.getPageSize());
			int totalCount = this.userMapper.getCount(page);
			System.out.println(totalCount);
			page.setTotalCount(totalCount);

			int totalPage = page.getTotalPage();
			// 处理前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 处理后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取超链接的个数
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			page.setData(this.userMapper.getUsers(page));

			if (page.getData() != null) {
				result.setStatus(1);
				result.setData(page);
			} else {
				result.setStatus(0);
				result.setMessage("没有用户信息");
			}
		}else{
			//查询指定角色
			page.setUserKeyword("undefinded".equals(page.getUserKeyword()) ? "%%" :"%"+page.getUserKeyword()+"%");
			page.setPageSize(pageUtil.getPageSize());

			int totalCount = this.userMapper.getCountByRole(page);
			page.setTotalCount(totalCount);

			int totalPage = page.getTotalPage();

			// 处理前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 处理后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取超链接的个数
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			page.setData(this.userMapper.getUsersByRole(page));
			System.out.println(page);
			if (page.getData() != null) {
				result.setStatus(1);
				System.out.println("111111111111");
				result.setData(page);
			} else {
				result.setStatus(0);
				result.setMessage("没有用户信息");
			}
		}
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void addUser(User user, String roleId, MultipartFile addPicture, HttpServletRequest request,
			HttpServletResponse response) {
		Result result = new Result();
		String imageFileName = null;
		// 获取创建用户的uuid
		String uuid = UUIDUtil.getUUID();
		// 获取上传文件的服务器路径
		String realPath = request.getServletContext().getRealPath("/head");
		File realPathFile = new File(realPath);
		System.out.println(realPathFile);
		if (!realPathFile.exists())
			realPathFile.mkdir();
		// 创建User对象
		if (addPicture == null || addPicture.isEmpty()) {
			// PrintWriterUtil.printMessageToClient(response, "请选择文件！");
			user.setHead("size.png");
			return;
		} else {
			// 文件存在，获取文件的相关信息
			String originalFileName = addPicture.getOriginalFilename();
			String name = addPicture.getName();
			String contentype = addPicture.getContentType();
			long size = addPicture.getSize();
			System.out.println("originalFileName-->" + originalFileName + "  name" + name + " contentype" + contentype
					+ "  size" + size+" roleid"+roleId);
			// 开始文件上传
			File serverPath = new File(realPath, originalFileName);
			try {
				// 文件上传
				if (!CommonValue.contentTypes.contains(contentype)) {
					PrintWriterUtil.printMessageToClient(response, "文件不匹配！");
					return;
				}
				if (size > 4194304) {
					PrintWriterUtil.printMessageToClient(response, "文件应该小于4M！");
					return;
				}
				// addPicture.transferTo(serverPath);
				// 能上传，能改变大小，加水印
				boolean flag = UploadUtil.uploadImage(addPicture, uuid, true, 640, realPath);
				if (!flag) {
					PrintWriterUtil.printMessageToClient(response, "文件上传失败！");
					return;
				}
				// 文件上传成功
				String originalExtendName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				imageFileName = uuid + "." + originalExtendName;
				user.setHead(imageFileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			// 调用添加用户数据库操作
			user.setId(uuid);
			this.userMapper.addUser(user);
			UserRole ur = new UserRole();
			ur.setUserId(uuid);
			ur.setRoleId(roleId);
			// 存储数据到
			this.userMapper.addUserRole(ur);
			PrintWriterUtil.printMessageToClient(response, "用户添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//能执行到此说明操作数据有异常,但图片上传成功了,图片不留,删除
			File file = new File(realPath + File.pathSeparator + imageFileName);
			if (file.exists()) {
				file.delete();
			}
			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub

	}
}
