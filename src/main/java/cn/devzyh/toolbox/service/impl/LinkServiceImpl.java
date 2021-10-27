package cn.devzyh.toolbox.service.impl;

import cn.devzyh.toolbox.domain.Link;
import cn.devzyh.toolbox.mapper.LinkMapper;
import cn.devzyh.toolbox.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public Map<String, List<Link>> getLinks() {
        Map<String, List<Link>> result = new HashMap<>();
        List<Link> links = linkMapper.findAll();
        links.forEach(link -> {
            List<Link> list = result.get(link.getItem());
            if (list == null) {
                list = new LinkedList<>();
            }

            list.add(link);
            result.put(link.getItem(), list);
        });

        return result;
    }
}
