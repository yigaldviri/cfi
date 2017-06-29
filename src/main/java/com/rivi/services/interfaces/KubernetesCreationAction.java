package com.rivi.services.interfaces;

import io.fabric8.kubernetes.api.model.HasMetadata;
import java.util.List;

/**
 * Created by dviyi01 on 6/26/2017.
 *
 */

public interface KubernetesCreationAction {
    List<String> execute(List<HasMetadata> hasMetadatas);
}
