package org.azamorano.simplescheduler.restcontroller;

import org.azamorano.simplescheduler.restcontroller.exception.LectureException;
import org.azamorano.simplescheduler.restcontroller.model.request.LectureRequest;
import org.azamorano.simplescheduler.service.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LectureControllerTest {
    @Mock
    private LectureService lectureService;

    private LectureController lectureController;

    @BeforeEach
    public void setUp() {
        this.lectureController = new LectureController(lectureService);
    }

    @Test
    public void shouldCallSaveLecture() {
        this.lectureController.saveLecture(LectureRequest.builder().build());
        verify(lectureService).saveLecture(any(LectureRequest.class));
    }

    @Test
    public void shouldCallDeleteLecture() {
        this.lectureController.deleteLecture("ANY-999");
        verify(lectureService).removeLecture(any(String.class));
    }

    @Test
    public void shouldThrowExceptionWhenDeleteLecture() {
        this.lectureController.deleteLecture("ANY-999");
        verify(lectureService).removeLecture("ANY-999");
        doThrow(LectureException.class).when(lectureService).removeLecture("ANY-999");
        assertThatThrownBy(() -> this.lectureController.deleteLecture("ANY-999"))
                .isInstanceOf(LectureException.class);
    }
}
