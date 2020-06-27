package com.zos.ksdsm.service;

import com.zos.ksdsm.domain.JobInfo;
import com.zos.ksdsm.domain.JobOutputListItem;
import com.zos.ksdsm.util.ZosmfUtil;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: be
 * @description: service for jcl submit
 * @author: Yu Liu
 * @create: 2020/06/27
 **/
@Service
public class JclService {
    /**
     * submit a jcl and get all the output
     */
    private String addJcl1="//ADDKSDS JOB CLASS=A,MSGCLASS=C,MSGLEVEL=(1,1),     \n" +
            "// NOTIFY=&SYSUID                                    \n" +
            "//STEP1  EXEC PGM=IDCAMS,REGION=0M                   \n" +
            "//SYSPRINT DD  SYSOUT=*                              \n" +
            "//SYSIN    DD  *                                     \n" +
            "   DEFINE CLUSTER (NAME(";
    private String addJcl2=")  -      \n" +
            "   VOLUMES(BYWK00)                         -         \n" +
            "   KEYS(6 1)                               -         \n" +
            "   RECSZ(80 80)                            -         \n" +
            "   TRACKS(1,1)                             -         \n" +
            "   CISZ(4096)                              -         \n" +
            "   FREESPACE(3 3) )                        -         \n" +
            "   DATA (NAME(";
    private String addJcl3=".DATA))      -      \n" +
            "   INDEX (NAME(";
    private String addJcl4=".INDEX))           \n" +
            "/*        ";


    private String deleteJcl1="//DELKSDS JOB CLASS=A,MSGCLASS=C,MSGLEVEL=(1,1),     \n" +
            "// NOTIFY=&SYSUID                                    \n" +
            "//STEP2 EXEC PGM=IDCAMS\n" +
            "//SYSPRINT DD  SYSOUT=*\n" +
            "//SYSIN    DD  *\n" +
            "   DELETE ";
    private String deleteJcl2=" CLUSTER\n" +
            "/*";

    public List<JobOutputListItem> submitJCL(HttpSession session, String jcl) {
        // submit job
        JobInfo jobInfo = ZosmfUtil.go(session, "/zosmf/restjobs/jobs", HttpMethod.PUT, jcl, null, JobInfo.class);
        if (jobInfo != null && ZosmfUtil.isReady(session, "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId(), 20)) {
            // get output list
            String outputListPath = "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId() + "/files";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jobOutput = ZosmfUtil.go(session, outputListPath, HttpMethod.GET, null, null, List.class);
            List<JobOutputListItem> result = new ArrayList<>();

            for (Map<String, Object> map : jobOutput) {
                // resolve list item
                JobOutputListItem item = new JobOutputListItem();
                item.setId((int) map.get("id"));
                item.setDdName(map.get("ddname").toString());
                item.setJobId(map.get("jobid").toString());
                item.setJobName(map.get("jobname").toString());
                item.setStepName(map.get("stepname").toString());
                item.setSubSystem(map.get("subsystem").toString());
                String outputPath = outputListPath + "/" + item.getId() + "/records";
                // get output of every list item
                try {
                    item.setOutput(ZosmfUtil.go(session, outputPath, HttpMethod.GET, null, null, String.class));
                } catch (Exception e) {
                    item.setOutput("");
                    e.printStackTrace();
                }
                result.add(item);
            }
            return result;
        }
        return null;
    }
    public List<JobOutputListItem> submitAddJCL(HttpSession session, String name) {
        String jcl=addJcl1+name+addJcl2+name+addJcl3+name+addJcl4;
        // submit job
        JobInfo jobInfo = ZosmfUtil.go(session, "/zosmf/restjobs/jobs", HttpMethod.PUT, jcl, null, JobInfo.class);
        if (jobInfo != null && ZosmfUtil.isReady(session, "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId(), 20)) {
            // get output list
            String outputListPath = "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId() + "/files";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jobOutput = ZosmfUtil.go(session, outputListPath, HttpMethod.GET, null, null, List.class);
            List<JobOutputListItem> result = new ArrayList<>();

            for (Map<String, Object> map : jobOutput) {
                // resolve list item
                JobOutputListItem item = new JobOutputListItem();
                item.setId((int) map.get("id"));
                item.setDdName(map.get("ddname").toString());
                item.setJobId(map.get("jobid").toString());
                item.setJobName(map.get("jobname").toString());
                item.setStepName(map.get("stepname").toString());
                item.setSubSystem(map.get("subsystem").toString());
                String outputPath = outputListPath + "/" + item.getId() + "/records";
                // get output of every list item
                try {
                    item.setOutput(ZosmfUtil.go(session, outputPath, HttpMethod.GET, null, null, String.class));
                } catch (Exception e) {
                    item.setOutput("");
                    e.printStackTrace();
                }
                result.add(item);
            }
            return result;
        }
        return null;
    }


    public List<JobOutputListItem> submitDeleteJCL(HttpSession session, String name) {
        String jcl=deleteJcl1+name+deleteJcl2;
        // submit job
        JobInfo jobInfo = ZosmfUtil.go(session, "/zosmf/restjobs/jobs", HttpMethod.PUT, jcl, null, JobInfo.class);
        if (jobInfo != null && ZosmfUtil.isReady(session, "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId(), 20)) {
            // get output list
            String outputListPath = "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId() + "/files";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jobOutput = ZosmfUtil.go(session, outputListPath, HttpMethod.GET, null, null, List.class);
            List<JobOutputListItem> result = new ArrayList<>();

            for (Map<String, Object> map : jobOutput) {
                // resolve list item
                JobOutputListItem item = new JobOutputListItem();
                item.setId((int) map.get("id"));
                item.setDdName(map.get("ddname").toString());
                item.setJobId(map.get("jobid").toString());
                item.setJobName(map.get("jobname").toString());
                item.setStepName(map.get("stepname").toString());
                item.setSubSystem(map.get("subsystem").toString());
                String outputPath = outputListPath + "/" + item.getId() + "/records";
                // get output of every list item
                try {
                    item.setOutput(ZosmfUtil.go(session, outputPath, HttpMethod.GET, null, null, String.class));
                } catch (Exception e) {
                    item.setOutput("");
                    e.printStackTrace();
                }
                result.add(item);
            }
            return result;
        }
        return null;
    }


    /**
     * submit a jcl for a specific output item
     *
     * @param id id of that output
     */
    @SuppressWarnings("SameParameterValue")
    String submitJCL(HttpSession session, String jcl, int id) {
        JobInfo jobInfo = ZosmfUtil.go(session, "/zosmf/restjobs/jobs", HttpMethod.PUT, jcl, null, JobInfo.class);
        if (jobInfo != null && ZosmfUtil.isReady(session, "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId(), 20)) {
            String outputPath = "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId() + "/files/" + id + "/records";
            try {
                return ZosmfUtil.go(session, outputPath, HttpMethod.GET, null, null, String.class);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return null;
    }
}
