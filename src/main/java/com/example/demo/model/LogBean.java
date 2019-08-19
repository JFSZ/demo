package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author chenxue
 * @Description
 * @Date 2019/8/14 10:09
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_log")
public class LogBean {
    @TableId
    private Integer id;
    private String methodName;
    private String operateName;
    private Date operateTime;
}
