package com.deppon.demo.batch.FTF;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 配置文件5个，一个是公共的，在每个类别的配置文件中都进行导引入了。
 * 如：读取文件----处理---写入文件  对应配置文件batch_FTF.xml，对应的包是FTF,启动方法名称：RunFTF();
 * inputFile.csv是输入文件，product.sql 是数据库建表语句，在数据库到数据库的demo中，新旧库都是相同表
 */
//@SpringBootApplication
public class MybatchApplication {
	/**
     * 启动  文件>>文件
     * 注意：此处读取的文件是绝对路径，请修改为你那边的路径
     */
    public  void RunFTF() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("batch_FTF.xml");
        JobLauncher launcher = (JobLauncher) applicationContext.getBean("jobLauncher");
        Job job = (Job) applicationContext.getBean("FTFJOB");
        //构建参数
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        //inputFile是配置文件中的value="file:#{jobParameters['inputFile']}
        JobParameters jobParameters = jobParametersBuilder.addString("inputFile", "D:/GitHub/javaDemo/demo/demo-batch/src/main/resources/inputFile.csv").toJobParameters();
        long start = System.currentTimeMillis();
        try {
            launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("---耗时：" + (end - start) / 1000 + "秒");
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        new MybatchApplication().RunFTF();
    }
}
