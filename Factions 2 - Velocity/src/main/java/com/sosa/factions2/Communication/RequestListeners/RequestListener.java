package com.sosa.factions2.Communication.RequestListeners;

import dev.westernpine.pipelines.api.Request;
import dev.westernpine.pipelines.api.Response;

/**
 * This interface is a base for plugin communication requests.
 */
public interface RequestListener {

    Response respondToRequest(Request request);

}
