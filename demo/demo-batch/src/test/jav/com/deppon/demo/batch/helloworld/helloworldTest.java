package com.deppon.demo.batch.helloworld;


import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class helloworldTest {
	@Test
   public void mian(){
		ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring_batch_helloworld.xml");
        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("helloWorldJob");

        try {
            /* 运行Job */
            JobExecution result = launcher.run(job, new JobParameters());
            /* 处理结束，控制台打印处理结果 */
            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
}