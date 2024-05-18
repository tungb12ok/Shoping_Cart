package dao;

import controller.*;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Blog;
import model.BlogCategories;
import model.Tag;

/**
 *
 * @author 84355
 */
public class BlogDAO extends DBContext {

    public List<Blog> getAllBlog(Integer quantity) {
        List<Blog> list = new ArrayList<>();
        String query;
        if (quantity == null) {
            query = "SELECT id, user_id, blog_cate_id, title, content, cover_img, main_img, description, created_at, modified_at FROM blogs";
        } else {
            query = "SELECT TOP (?) id, user_id, blog_cate_id, title, content, cover_img, main_img, description, created_at, modified_at FROM blogs ORDER BY created_at DESC";
        }
        try (PreparedStatement st = connection.prepareStatement(query)) {
            if (quantity != null) {
                st.setInt(1, quantity);
            }
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    Blog p = new Blog();
                    p.setId(resultSet.getInt("id"));
                    p.setUser_id(resultSet.getInt("user_id"));
                    p.setBlog_cate_id(resultSet.getInt("blog_cate_id"));
                    p.setTitle(resultSet.getString("title"));
                    p.setContext(resultSet.getString("content"));
                    p.setCover_img(resultSet.getString("cover_img"));
                    p.setMain_img(resultSet.getString("main_img"));
                    p.setDescription(resultSet.getString("description"));
                    p.setCreated_at(resultSet.getDate("created_at"));
                    p.setModified_at(resultSet.getDate("modified_at"));
                    BlogCategories blogCategory = new BlogCategoriesDAO().getByID(resultSet.getInt("blog_cate_id"));
                    p.setBlogCategory(blogCategory);
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Blog> getAllBlog(int currentPage, int limitSize) {
        List<Blog> list = new ArrayList<>();
        // Edit query with other entity
        String query = "SELECT id, user_id, blog_cate_id, title, content, cover_img, main_img, description, created_at, modified_at "
                // Select total of blog
                + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY created_at DESC) AS RowNum, * FROM blogs) AS RowConstrainedResult "
                + "WHERE RowNum > ? AND RowNum <= ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            int offset = (currentPage - 1) * limitSize;
            st.setInt(1, offset);
            st.setInt(2, offset + limitSize);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    Blog p = new Blog();
                    p.setId(resultSet.getInt("id"));
                    p.setUser_id(resultSet.getInt("user_id"));
                    p.setBlog_cate_id(resultSet.getInt("blog_cate_id"));
                    p.setTitle(resultSet.getString("title"));
                    p.setContext(resultSet.getString("content"));
                    p.setCover_img(resultSet.getString("cover_img"));
                    p.setMain_img(resultSet.getString("main_img"));
                    p.setDescription(resultSet.getString("description"));
                    p.setCreated_at(resultSet.getDate("created_at"));
                    p.setModified_at(resultSet.getDate("modified_at"));
                    BlogCategories blogCategory = new BlogCategoriesDAO().getByID(resultSet.getInt("blog_cate_id"));
                    p.setBlogCategory(blogCategory);
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Blog getByID(int id) {
        Blog blog = null;
        String query = "SELECT id, user_id, blog_cate_id, title, content, cover_img, main_img, description, created_at, modified_at FROM blogs WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    blog = new Blog();
                    blog.setId(resultSet.getInt("id"));
                    blog.setUser_id(resultSet.getInt("user_id"));
                    blog.setBlog_cate_id(resultSet.getInt("blog_cate_id"));
                    blog.setTitle(resultSet.getString("title"));
                    blog.setContext(resultSet.getString("content"));
                    blog.setCover_img(resultSet.getString("cover_img"));
                    blog.setMain_img(resultSet.getString("main_img"));
                    blog.setDescription(resultSet.getString("description"));
                    blog.setCreated_at(resultSet.getDate("created_at"));
                    blog.setModified_at(resultSet.getDate("modified_at"));
                    BlogCategories blogCategory = new BlogCategoriesDAO().getByID(resultSet.getInt("blog_cate_id"));
                    blog.setBlogCategory(blogCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    public List<Blog> filterBlog(String title, List<Tag> listTag, Integer categoryId) {
        List<Blog> filteredBlogs = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT id, user_id, blog_cate_id, title, content, cover_img, main_img, description, created_at, modified_at FROM blogs WHERE 1 = 1");

        if (title != null) {
            queryBuilder.append(" AND title LIKE ?");
        }

        if (listTag != null && !listTag.isEmpty()) {
            queryBuilder.append(" AND id IN (SELECT blog_id FROM blog_tags WHERE tag_id IN (");
            for (int i = 0; i < listTag.size(); i++) {
                if (i != 0) {
                    queryBuilder.append(",");
                }
                queryBuilder.append("?");
            }
            queryBuilder.append("))");
        }

        if (categoryId != null) {
            queryBuilder.append(" AND blog_cate_id = ?");
        }

        try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;
            if (title != null) {
                statement.setString(parameterIndex++, "%" + title + "%");
            }
            if (listTag != null) {
                for (Tag tag : listTag) {
                    statement.setInt(parameterIndex++, tag.getId());
                }
            }
            if (categoryId != null) {
                statement.setInt(parameterIndex++, categoryId);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog p = new Blog();
                    p.setId(resultSet.getInt("id"));
                    p.setUser_id(resultSet.getInt("user_id"));
                    p.setBlog_cate_id(resultSet.getInt("blog_cate_id"));
                    p.setTitle(resultSet.getString("title"));
                    p.setContext(resultSet.getString("content"));
                    p.setCover_img(resultSet.getString("cover_img"));
                    p.setMain_img(resultSet.getString("main_img"));
                    p.setDescription(resultSet.getString("description"));
                    p.setCreated_at(resultSet.getDate("created_at"));
                    p.setModified_at(resultSet.getDate("modified_at"));
                    BlogCategories blogCategory = new BlogCategoriesDAO().getByID(resultSet.getInt("blog_cate_id"));
                    p.setBlogCategory(blogCategory);
                    filteredBlogs.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
        }

        return filteredBlogs;
    }

    public Map<BlogCategories, List<Blog>> getBlogsByCategory() {
        Map<Integer, List<Blog>> blogMap = new HashMap<>();
        String query = "SELECT id, user_id, blog_cate_id, title, content, cover_img, main_img, description, created_at, modified_at FROM blogs";
        try (PreparedStatement st = connection.prepareStatement(query); ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                Blog p = new Blog();
                p.setId(resultSet.getInt("id"));
                p.setUser_id(resultSet.getInt("user_id"));
                p.setBlog_cate_id(resultSet.getInt("blog_cate_id"));
                p.setTitle(resultSet.getString("title"));
                p.setContext(resultSet.getString("content"));
                p.setCover_img(resultSet.getString("cover_img"));
                p.setMain_img(resultSet.getString("main_img"));
                p.setDescription(resultSet.getString("description"));
                p.setCreated_at(resultSet.getDate("created_at"));
                p.setModified_at(resultSet.getDate("modified_at"));

                int categoryId = resultSet.getInt("blog_cate_id");

                // Kiểm tra xem danh mục đã tồn tại trong HashMap chưa
                if (!blogMap.containsKey(categoryId)) {
                    // Nếu không, tạo một danh sách mới cho danh mục
                    blogMap.put(categoryId, new ArrayList<>());
                }
                // Thêm bài viết vào danh sách bài viết cho danh mục
                blogMap.get(categoryId).add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Chuyển đổi danh sách bài viết của từng danh mục thành danh mục thực tế và trả về
        Map<BlogCategories, List<Blog>> result = new HashMap<>();
        for (Map.Entry<Integer, List<Blog>> entry : blogMap.entrySet()) {
            BlogCategories blogCategory = new BlogCategoriesDAO().getByID(entry.getKey());
            result.put(blogCategory, entry.getValue());
        }

        return result;
    }

    public static void main(String[] args) {
        BlogDAO bDAO = new BlogDAO();
        System.out.println(bDAO.filterBlog("aaa", null, null).size());

    }
}
