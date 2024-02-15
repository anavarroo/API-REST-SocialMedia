      package com.API_REST.persistence.repository;

      import com.API_REST.persistence.model.Follow;
      import com.API_REST.persistence.model.User;
      import org.springframework.data.jpa.repository.JpaRepository;

      import java.util.List;

      public interface FollowRepositoryI extends JpaRepository<Follow, Long> {

            List<Follow> findByFollower(User follower);

            List<Follow> findByFollowing(User following);

            List<Follow> findByFollowerAndFollowing(User follower, User following);



      }
