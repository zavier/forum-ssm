package com.forum.topic.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.Topic;
import com.forum.entity.User;
import com.forum.topic.service.TopicService;
import com.forum.util.UUIDUtil;

/*
 * 主题帖控制器 包括发表主题帖子、回复帖子、删除帖子、设精华主题帖等
 */
@Controller
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * 列出论坛模块下的主题帖子
     * 
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/listBoardTopics/{boardId}", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public List<Topic> listBoardTopics(@PathVariable String boardId) {
        return topicService.getPagedTopics(boardId);
    }

    /**
     * 新增主题帖子的页面
     * 
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/addTopicPage/{boardId}", method = RequestMethod.GET)
    public ModelAndView addTopicPage(@PathVariable String boardId) {
        ModelAndView view = new ModelAndView();
        view.addObject("boardId", boardId);
        view.setViewName("/topic/addTopicPage");
        return view;
    }

    /**
     * 新增主题帖
     * 
     * @param request
     * @param boardId
     * @param topic
     * @return
     */
    @RequestMapping(value = "/addTopic/{boardId}", method = RequestMethod.POST)
    public String addTopic(HttpServletRequest request, @PathVariable String boardId, Topic topic) {
        User user = (User) request.getSession().getAttribute("LOGINUSER");
        topic.setId(UUIDUtil.getUUIDPrimaryKey());
        topic.setUserId(user.getId());
        Date now = new Date();
        topic.setCreateTime(now);
        topic.setLastPost(now);
        topic.setTopicView(1);
        topic.setTopicReplies(0);
        topic.setDigest(0);
        topicService.addTopic(topic, boardId);
        return "redirect:/";
    }

    /**
     * 查看主题帖详细信息
     * 
     * @param topicId
     * @return
     */
    @RequestMapping(value = "/showTopicDetails/{topicId}")
    public ModelAndView showTopicDetails(@PathVariable String topicId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("/topic/showTopicDetails");
        Topic topic = topicService.getTopicById(topicId);
        view.addObject("topic", topic);
        return view;
    }
}
