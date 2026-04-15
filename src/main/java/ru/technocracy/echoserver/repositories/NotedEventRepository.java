package ru.technocracy.echoserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.technocracy.echoserver.models.notedevent.NoteType;
import ru.technocracy.echoserver.models.notedevent.NotedEvent;
import ru.technocracy.echoserver.models.notedevent.NotedEventPK;
import java.util.List;

public interface NotedEventRepository extends JpaRepository<NotedEvent, NotedEventPK> {

    @Query("SELECT nt.id.eventId FROM NotedEvent nt WHERE nt.id.userId =: userId")
    public List<Long> getNotedEventsByUserId(@Param("userId")Long userId);

    @Query("SELECT nt.id.eventId FROM NotedEvent nt" +
            " WHERE nt.noteType =: status AND nt.id.userId =: userId")
    public List<Long> getEventByUserIdWhereStatusIs(@Param("userId") Long userId,
                                                         @Param("status") NoteType status);

    public NotedEvent getNotedEventById(NotedEventPK notedEventPK);

    @Modifying
    @Query("UPDATE NotedEvent ne SET ne.noteType =: newNoteType WHERE ne.id =: notedEventPk")
    public void update(@Param("notedEventPk")NotedEventPK notedEventPK, @Param("newNoteType") NoteType newNoteType);

    public void deleteNotedEventById(NotedEventPK notedEventPK);
}
