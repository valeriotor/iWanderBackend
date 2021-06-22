package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.core.DayComment;
import com.valeriotor.iWanderBackend.model.dto.CommentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayCommentsRepo extends JpaRepository<DayComment, Long> {
    List<CommentDTO> findByDay_Id(long dayId, Pageable pageable);
    List<CommentDTO> findAllByDay_IdIn(List<Long> dayIds, Pageable pageable);

    @Modifying
    @Query("delete from DayComment d where d.day = ?1")
    void deleteCommentsWithDayId(Day id);

}
