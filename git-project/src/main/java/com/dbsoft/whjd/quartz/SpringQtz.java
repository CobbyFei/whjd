package com.dbsoft.whjd.quartz;

import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean;  
  
import java.util.Date;  
  
public class SpringQtz {  
    private static int counter = 0;  
    protected void execute()  {  
        long ms = System.currentTimeMillis();  
        System.out.println("\t\t" + new Date(ms));  
        System.out.println("(" + counter++ + ")");  
    }  
}