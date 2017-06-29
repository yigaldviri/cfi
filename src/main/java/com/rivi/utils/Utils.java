package com.rivi.utils;

import com.rivi.model.Base;
import com.rivi.model.GitHubHook;
import io.fabric8.kubernetes.api.model.*;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rivi on 26/06/17.
 *
 */

public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class);

    public static Job createJobObject(GitHubHook gitHubHook) {
        logger.debug("Creating Job Object");

        // MetaData
        ObjectMeta objectMeta = getObjectMeta();
        List<String> commands = getCommands(gitHubHook);

        Map<String, String> matchLabels = new HashMap<>();
        matchLabels.put(Constants.JOB_LABEL, Constants.JOB_POD_CONTAINER_NAME);

        // Job Spec
        JobSpec jobSpec = new JobSpec();
        PodTemplateSpec podTemplateSpec = new PodTemplateSpec();
        objectMeta.setLabels(matchLabels);

        // Pod Spec
        PodSpec podSpec = new PodSpec();
        Container container = getContainer(commands);
        podSpec.setContainers(Arrays.asList(container));
        podSpec.setRestartPolicy(Constants.JOB_RESTART_POLICY);
        jobSpec.setActiveDeadlineSeconds(Constants.JOB_TIMEOUT_SECONDS);

        podTemplateSpec.setSpec(podSpec);
        jobSpec.setTemplate(podTemplateSpec);

        return new Job(Constants.JOB_API, Constants.JOB_KIND, objectMeta, jobSpec, null);

    }

    private static Container getContainer(List<String> commands) {
        Container container = new Container();
        container.setName(Constants.JOB_POD_NAME);
        container.setImage(Constants.JOB_DOCKER_IMAGE);
        container.setImagePullPolicy(Constants.IMAGE_PULL_POLICY);
        container.setCommand(commands);
        SecurityContext securityContext = new SecurityContext();
        securityContext.setPrivileged(true);
        container.setSecurityContext(securityContext);
        return container;
    }

    private static List<String> getCommands(GitHubHook gitHubHook) {
        List<String> commands = new ArrayList<>();
        Collections.addAll(commands, Constants.JOB_POD_BASE_COMMANDS);
        commands.add(gitHubHook.getRepository().getClone_url());
        return commands;
    }


    private static ObjectMeta getObjectMeta() {
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(Constants.JOB_POD_NAME + System.currentTimeMillis());
        objectMeta.setNamespace(Constants.CLUSTER_NAMESPACE);
        return objectMeta;
    }


    public static String parseGitHubData(GitHubHook gitHubHook) {
        StringBuilder sb = new StringBuilder();

        String html_url = gitHubHook.getRepository().getClone_url();
        Base base = gitHubHook.getPull_request().getBase();
        String ref = base.getRef();
        String sha = base.getSha();
        String user = base.getUser().getLogin();
        String userType = base.getUser().getType();

        sb.
                append("new GitHub hook - new build. By ").
                append(userType).
                append(" ").
                append(user).
                append(" URL - ").
                append(html_url).
                append(" with ref and sha ").
                append(ref).append(sha);

        return sb.toString();
    }
}
