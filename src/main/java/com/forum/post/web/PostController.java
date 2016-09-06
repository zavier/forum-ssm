package com.forum.post.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.Post;
import com.forum.entity.Topic;
import com.forum.post.service.PostService;
import com.forum.topic.service.TopicService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    /**
     * 列出主题下的所有帖子
     * 
     * @param topicId
     * @return
     */
    @RequestMapping(value = "/listTopicPosts-{topicId}", method = RequestMethod.GET)
    public ModelAndView listTopicPosts(@PathVariable String topicId) {
        ModelAndView view = new ModelAndView();
        Topic topic = topicService.getTopicById(topicId);
        List<Post> pagedPost = postService.getPagedPosts(topicId);
        view.addObject("topic", topic);
        view.addObject("pagedPost", pagedPost);
        view.setViewName("/post/listTopicPosts");
        return view;
    }
}
