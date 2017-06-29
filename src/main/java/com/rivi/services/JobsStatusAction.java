package com.rivi.services;

import com.rivi.dao.JobsDao;
import com.rivi.services.interfaces.KuberenetsStatusAction;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by rivi on 26/06/17.
 *
 */

@Service
public class JobsStatusAction implements KuberenetsStatusAction {

    private static final Logger logger = Logger.getLogger(JobsStatusAction.class);

    private KubernetesClient kubernetesClient;
    private JobsDao jobsDao;

    @Inject
    public JobsStatusAction(KubernetesClient kubernetesClient,
                            JobsDao jobsDao){
        this.kubernetesClient = kubernetesClient;
        this.jobsDao = jobsDao;
    }

    @Override
    public String getStatus() {
        List<Pod> pods = kubernetesClient.pods().list().getItems();

        String message = listAllPods(pods);
        logger.info(message);
        return message;
    }

    private String listAllPods(List<Pod> pods) {
        StringBuilder sb = new StringBuilder();
        sb.append("currents pods: ");
        if (pods!= null && !pods.isEmpty()) {
            for (Pod pod : pods) {
                sb.
                        append("Pod " ).
                        append(pod.getMetadata().getName() ).
                        append(" with status ").
                        append(pod.getStatus().getMessage()).
                        append(". IP - ").
                        append(pod.getStatus().getPodIP()).
                        append(". Containers status: ").
                        append(pod.getStatus().getContainerStatuses());
            }
            jobsDao.addRecord(sb.toString());
        }else{
            sb.append("None exists");
        }
        return sb.toString();
    }
}
