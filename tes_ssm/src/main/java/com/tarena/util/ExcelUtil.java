package com.tarena.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tarena.entity.Role;
import com.tarena.entity.User;

public class ExcelUtil {
	public static byte[] write2Excel(List<User> users){
		byte[] data=null;
		ByteArrayOutputStream out=null;
		
		try {
			//创建excel2003
			Workbook wb=new HSSFWorkbook();
			out=new ByteArrayOutputStream();
			//创建新的sheet
			Sheet sheet=wb.createSheet("allUsers");
			int columnCount=12;
			//创建第一栏(表头信息)
			Row row=sheet.createRow(0);
			String[] titleArray={
					"登录名",
					"登录类型",
					"昵称",
					"密码",
					"用户类型",
					"头像",
					"积分",
					"锁",
					"注册日期",
					"年龄",
					"性别",
					"角色"
			};
			for(int i=0;i<columnCount;i++){
				Cell cell=row.createCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				sheet.setColumnWidth(i, 6000);
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				short color=HSSFColor.RED.index;
				font.setColor(color);
				style.setFont(font);
				//填写数据
				cell.setCellStyle(style);
				cell.setCellValue(titleArray[i]);
			}
			//创建新行
			for(int j=0;j<users.size();j++){
				User user=users.get(j);
				row=sheet.createRow(j+1);
				
				row.createCell(0).setCellValue(user.getLoginName());
				row.createCell(1).setCellValue(user.getLoginType());
				row.createCell(2).setCellValue(user.getNickName());
				row.createCell(3).setCellValue(user.getPassword());
				if(user.getType()==0){
					row.createCell(4).setCellValue("学生");
				}else if(user.getType()==1){
					row.createCell(4).setCellValue("讲师");
				}else if(user.getType()==2){
					row.createCell(4).setCellValue("管理员");
				}else if(user.getType()==3){
					row.createCell(4).setCellValue("游客");
				}
				
				row.createCell(5).setCellValue(user.getHead());
				row.createCell(6).setCellValue(user.getScore());
				row.createCell(7).setCellValue(user.getIsLock());
				row.createCell(8).setCellValue(user.getRegDate().toLocaleString());
				row.createCell(9).setCellValue(user.getAge());
				row.createCell(10).setCellValue(user.getSex());
				
				if(user.getRoles()!=null && user.getRoles().size()>0){
					String roleName="";
					for(Role role : user.getRoles()){
						roleName+=role.getName()+",";
					}
					row.createCell(11).setCellValue(roleName.substring(0,roleName.length()-1));
				
				}else{
					row.createCell(11).setCellValue("无角色");
				}
			}
			wb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		data=out.toByteArray();
		return data;
	}
}
