package com.rivi;

import com.rivi.utils.Constants;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by rivi on 26/06/17.
 *
 */

@SpringBootApplication
public class Application {

    private static final Logger logger = Logger.getLogger(Application.class);

    public Application(){}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public KubernetesClient kubeClient() throws Exception {
        Config config = createClientConfig(
                Constants.CLUSTER_URL,
                Constants.CLUSTER_USER,
                Constants.CLUSTER_PASSWORD,
                Constants.CLUSTER_NAMESPACE);
            return new DefaultKubernetesClient(config);
    }

    private Config createClientConfig(String url, String username, String password, String clientNamespace) {
        logger.info("Creating Client With Config: url = " +url +", username = " + username +" , " +
                "password = '***', namespace = " +clientNamespace);
        return new ConfigBuilder().withMasterUrl(url)
                .withConnectionTimeout(Constants.CONNECTION_TIMEOUT)
                .withTrustCerts(true)
                .withNamespace(clientNamespace)

                //when using with local minikube
                .withCaCertFile("C:/Users/dviyi01/.minikube/apiserver.crt")
                .withClientKeyFile("C:/Users/dviyi01/.minikube/apiserver.key")

                //when using with normal Kube server
                //.withUsername(username)
                //.withPassword(password)

                .build();
    }


}
