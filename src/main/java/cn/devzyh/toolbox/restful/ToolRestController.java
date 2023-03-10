package cn.devzyh.toolbox.restful;

import org.springframework.scheduling.support.CronExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具接口
 *
 * @author devzyh
 */
@RestController
@RequestMapping("/tool")
public class ToolRestController {

    @PostMapping("/cron/run_time")
    public List<String> cronRunTime(@RequestParam String cron) {
        int size = 10;
        List<String> list = new ArrayList<>(size);
        try {
            CronExpression expression = CronExpression.parse(cron);
            LocalDateTime next = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            for (int i = 0; i < size; i++) {
                next = expression.next(next);
                list.add(formatter.format(next));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
