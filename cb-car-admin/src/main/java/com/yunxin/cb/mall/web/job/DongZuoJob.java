package com.yunxin.cb.mall.web.job;



public abstract class DongZuoJob  {
//	 protected org.apache.log4j.Logger logger = org.apache.log4j.LoggerFactory.getLogger(this.getClass());
//	    public static final String JOB_DATASOURCE = "datasource";
//	    public static final String JOB_QUARTZ = "quartzId";
//
//	    protected JobExecutionContext context;
//	   // protected QuartzService quartzService;
//
//	    @Override
//	    public void execute(JobExecutionContext context) throws JobExecutionException {
//	        this.context = context;
//	        //String jobName = context.getJobDetail().getName();
//	       // loggerInfo(1,jobName,DateUtils.dateToDateString(new Date(),DateUtils.MSEL_FORMAT));
//
//	        Long jobQuartzId = context.getJobDetail().getJobDataMap().getLong(JOB_QUARTZ);
//	        try{
//	            run();
//	            //UetMonitorHelper.saveQuartzLog(jobQuartzId, QuartzDomain.EXECUTE_STATUS_SUCCESS, "定时任务" + jobName + "操作成功！");
//	        }catch(Exception e){
//	           // UetMonitorHelper.saveQuartzLog(jobQuartzId, QuartzDomain.EXECUTE_STATUS_FAILURE, "定时任务" + jobName + "操作失败.");
//	        }
//	        //loggerInfo(2,jobName,DateUtils.dateToDateString(new Date(),DateUtils.MSEL_FORMAT));
//	    }
//
//	    public abstract void run();
//
//	    protected void loggerInfo(int type, String jobName, String dateStr){
//	        StringBuffer buff = new StringBuffer(400);
//	        switch(type){
//	            case 1:
//	                buff.append("  -------Start Job: ");
//	                break;
//	            case 2:
//	                buff.append(" -------End Job: ");
//	                break;
//	        }
//	        buff.append(jobName).append("-----, at ").append(dateStr).append("-----");
//	        logger.info(buff);
//	        buff = null;
//	    }
//

}
