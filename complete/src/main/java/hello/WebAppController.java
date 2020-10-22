package hello;

import hello.PubSubApplication.PubsubOutboundGateway;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class WebAppController {

  private static final Log LOGGER = LogFactory.getLog(PubSubApplication.class);

  // tag::autowireGateway[]
  @Autowired
  private PubsubOutboundGateway messagingGateway;
  // end::autowireGateway[]

  @PostMapping("/publishMessage")
  public RedirectView publishMessage(@RequestParam("message") String message) {
    if (messagingGateway == null)
      LOGGER.error("messageGateway is NULL.");
    else {
      messagingGateway.sendToPubsub(message);
      LOGGER.info("messageGateway passed.");
    }
    
    return new RedirectView("/");
  }
}
