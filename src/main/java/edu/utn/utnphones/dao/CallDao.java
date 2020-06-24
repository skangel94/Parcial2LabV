package edu.utn.utnphones.dao;

import edu.utn.utnphones.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CallDao extends JpaRepository<Call,Integer> {

    @Procedure(value = "sp_insertcall")
    void addCall(String lineFrom, String lineTo, int seg, Date date);

    @Query(value = "select * from calls c inner join phone_lines pl on c.call_line_id_from = pl.line_id \n" +
            "inner join users u on pl.line_user_id = u.user_id \n" +
            "where c.call_date >= ?1  and c.call_date <= ?2 and u.user_id = ?3",nativeQuery = true)
    List<Call> getCallsByDate(Date dateFrom, Date dateTo, int userId);

    @Query(value = "select * from calls c inner join phone_lines pl on c.call_line_id_from = pl.line_id \n" +
            "inner join users u on pl.line_user_id = u.user_id \n" +
            "where c.call_duration_seg = ?1 and u.user_id = pl.line_user_id",nativeQuery = true)
    List<Call> getCallsByDuration(int duration);

    @Query(value = "select * from calls c \n" +
            "inner join phone_lines pl on pl.line_id = c.call_line_id_from \n" +
            "inner join users u on u.user_id = pl.line_user_id \n" +
            "inner join cities ci on ci.city_id = u.user_city_id \n" +
            "inner join provinces p on p.province_id = ci.city_province_id \n" +
            "where p.province_name like ?1 and (u.user_dni % 2) <> 0",nativeQuery = true)
    List<Call> getCallsByProvinceDniOdd(String province);

    @Query(value = "select * from calls c \n" +
            "inner join phone_lines pl on pl.line_id = c.call_line_id_from \n" +
            "inner join users u on u.user_id = pl.line_user_id \n" +
            "inner join cities ci on ci.city_id = u.user_city_id \n" +
            "inner join provinces p on p.province_id = ci.city_province_id \n" +
            "where p.province_name like ?1 and (u.user_dni % 2) = 0",nativeQuery = true)
    List<Call> getCallsByProvinceDniEven (String province);
}
