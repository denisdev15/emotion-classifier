package org.sindo.web;

import org.json.simple.JSONObject;
import org.sindo.Service.ClassifierService;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;

/**
 * Created by gustavo on 08/06/18.
 */
@RestController
@RequestMapping("/process")
public class WebController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String processData(@RequestBody String comment) {
//        String comment = "";
        System.out.println(comment);
        String jsonText = "";
        try {
            JSONObject response = ClassifierService.classify(comment);


            StringWriter out = new StringWriter();
            response.writeJSONString(out);

            jsonText = out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(jsonText);

        return jsonText;
    }
}
