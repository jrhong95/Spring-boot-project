package com.example.communityweb;

import com.example.communityweb.domain.Board;
import com.example.communityweb.domain.User;
import com.example.communityweb.domain.enums.BoardType;
import com.example.communityweb.repository.BoardRepository;
import com.example.communityweb.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJdbcTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@test.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init(){
        User user = userRepository.save(User.builder()
                .name("havi")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build());

        boardRepository.save(Board.builder()
                    .title(boardTestTitle)
                    .subTitle("서브타이틀")
                    .content("콘텐츠")
                    .boardType(BoardType.free)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .user(user).build());
    }

    @Test
    public void 제대로_생성됐는지_테스트(){
        User user = userRepository.findByEmail(email);
        System.out.println("user = " + user);
        assertThat(user.getName(), is("havi"));
        assertThat(user.getPassword(), is("test"));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("서브타이틀"));
        assertThat(board.getContent(), is("콘텐츠"));
    }
}
