package cn.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

  @Excel(name="ID")
  private String id;
  @Excel(name="username")
  private String username;
  @Excel(name="phone")
  private String phone;
  @Excel(name="headimg",type = 2,width = 40,height = 40,imageType = 1)
  private String headimg;
  @Excel(name="brief")
  private String brief;
  @Excel(name="wechat")
  private String wechat;
  @Excel(name="createdate")
  private Date createdate;
  @Excel(name="status")
  private long status;



}
