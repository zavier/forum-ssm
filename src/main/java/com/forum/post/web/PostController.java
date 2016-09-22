package com.forum.post.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.Post;
import com.forum.entity.Topic;
import com.forum.post.service.PostService;
import com.forum.topic.service.TopicService;
import com.forum.util.entity.ResultSet;

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
        List<Post> pagedPost = postService.getPostsByTopicId(topicId);
        view.addObject("topic", topic);
        view.addObject("pagedPost", pagedPost);
        view.setViewName("/post/listTopicPosts");
        return view;
    }
    
    /**
     * 添加主题帖的评论
     * @param text 评论内容
     * @param topicId 主题帖id
     * @param boardId 板块id
     * @param userId 评论用户id
     * @return
     */
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    @ResponseBody
    public ResultSet<String> addPost(String text, String topicId, String boardId, String userId) {
        Post post = new Post();
        post.setTopicId(topicId);
        post.setBoardId(boardId);
        post.setUserId(userId);
        post.setPostText(text);
        postService.addPost(post);
        
        ResultSet<String> resultSet = new ResultSet<>();
        resultSet.setStateCode(ResultSet.RES_SUCCESS);
        return resultSet;
    }
}
