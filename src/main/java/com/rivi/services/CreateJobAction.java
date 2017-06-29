package com.rivi.services;

import com.rivi.dao.JobsDao;
import com.rivi.services.interfaces.KubernetesCreationAction;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rivi on 26/06/17.
 *
 */

@Service
public class CreateJobAction implements KubernetesCreationAction {

    private static final Logger logger = Logger.getLogger(CreateJobAction.class);

    private KubernetesClient kubernetesClient;
    private JobsDao jobsDao;

    @Inject
    public CreateJobAction(KubernetesClient kubernetesClient,
                           JobsDao jobsDao){
        this.kubernetesClient = kubernetesClient;
        this.jobsDao = jobsDao;
    }

    @Override
    public List<String> execute(List<HasMetadata> hasMetadatas) {
        List<HasMetadata> output = kubernetesClient.resourceList(hasMetadatas).createOrReplace();
        List<String> res = new ArrayList<>();
        for (HasMetadata hasMetadata : output) {
            String AsString = getJobData(hasMetadata);
            jobsDao.addRecord(AsString);
            res.add(AsString);
        }
        logger.info("Output is: " + output.toString());
        return res;
    }

    private String getJobData(HasMetadata hasMetadata) {
        StringBuilder sb = new StringBuilder();
        ObjectMeta metadata = hasMetadata.getMetadata();
        sb.
            append("Job: ").
            append(metadata.getName()).
            append(" Created at: ").
            append(metadata.getCreationTimestamp()).
            append(" successfully ");

        return sb.toString();
    }
}
