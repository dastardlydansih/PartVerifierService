package OpRouting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PartVerifier")
public class PVController {

    private static final String template = "Checking for value, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ParseINIRestService RestService = new ParseINIRestService();

    @RequestMapping(value = "/raw", method=RequestMethod.GET)
    public @ResponseBody ParseINIResponseRaw getINIValue(@RequestParam(value="group", required=true) String group, @RequestParam(value="key", required=true) String key) {
        return RestService.GetValueRequestRaw(group, key);
    }


    @RequestMapping(value = "/raw", method=RequestMethod.PUT)
    public @ResponseBody ParseINIResponseRaw setINIValue(@RequestParam(value="group", required=true) String group, @RequestParam(value="key", required=true) String key, @RequestParam(value="value", required=true) String value) {
        return RestService.PutValueRequestRaw(group, key, value);
    }


    @RequestMapping(value = "/raw", method=RequestMethod.POST)
    public @ResponseBody ParseINIResponseRaw addINISection(@RequestParam(value="group", required=true) String group, @RequestParam(value="key", required=false) String key, @RequestParam(value="value", required=false) String value) {
        return RestService.PostGroupRequestRaw(group, key, value);
    }

    @RequestMapping(value = "/partnumber", method=RequestMethod.GET)
    public @ResponseBody ParseINIResponsePartnumber getPNValue(@RequestParam(value="partNumber", required=true) String partNumber) {
        return RestService.GetPartNumberRequest(partNumber);
    }

    @RequestMapping(value = "/partnumber/attribute", method=RequestMethod.GET)
    public @ResponseBody ParseINIResponse getPNAttribute(@RequestParam(value="partNumber", required=true) String partNumber, @RequestParam(value="attribute", required=true) String key) {
        return RestService.GetValueRequest(partNumber, key);
    }

    @RequestMapping(value = "/partnumber/operations/routing", method=RequestMethod.GET)
    public @ResponseBody ParseINIResponse getOperations(@RequestParam(value="partNumber", required=true) String partNumber) {
        return RestService.GetValueRequest(partNumber, "OPERATIONS");
    }

    @RequestMapping(value = "/operation_ids", method=RequestMethod.GET)
    public @ResponseBody ParseINIResponseRaw getOpValueId(@RequestParam(value="Operation", required=true) String key) {
        return RestService.GetValueRequestRaw("OPERATION_IDS", key);
    }

    @RequestMapping(value = "/testers", method=RequestMethod.GET)
    public @ResponseBody ParseINIResponseRaw getTestersByOp(@RequestParam(value="Operation", required=true) String key) {
        return RestService.GetValueRequestRaw("TESTERS", key);
    }

}

