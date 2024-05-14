/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
/**
 *
 * @author 84355
 */
public class BlogDAO extends DBContext {
    
    public List<Blog> getAllBlog() {
        List<Blog> list = new ArrayList<>();
        String sql = "Slect * from blogs ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog p = new Blog();
                p.setId(rs.getInt("id"));
                p.setUser_id(rs.getInt("user_id"));
                p.setBlog_cate_id(rs.getInt("blog_cate_id"));
                p.setTitle(rs.getString("title"));
                p.setContext(rs.getString("context"));
                p.setCover_img(rs.getString("cover_img"));
                p.setMain_img(rs.getString("main_img"));
                p.setDescription(rs.getString("description"));
                p.setCreated_at(rs.getDate("created_at"));
                p.setModified_at(rs.getDate("modified_at"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
