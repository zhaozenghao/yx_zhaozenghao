package cn.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

  private String id;
  private String cateName;
  private long levels;
  private String parentId;

}
