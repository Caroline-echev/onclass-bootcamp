package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.data.VersionData;
import com.bootcamp.onclass.domain.exception.ValidateDateException;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.model.Version;
import com.bootcamp.onclass.domain.spi.IVersionPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VersionUseCaseTest {

    @Mock
    private IVersionPersistencePort versionPersistencePort;
    @InjectMocks
    private VersionUseCase versionUseCase;

    private VersionData versionData = new VersionData();
    @Test
    @DisplayName("Test successful adding of a version")
    void shouldAddVersion() {
        //GIVEN

        Version version = versionData.createVersion();

        //WHEN

        when(versionPersistencePort.addVersion(version)).thenReturn(version);
        versionUseCase.addVersion(version);

        //THEN

        verify(versionPersistencePort).addVersion(version);

    }
    @Test
    @DisplayName("Test unsuccessful adding of a technology")
     void ShouldAddVersionValidateDateException() {
        // GIVEN
        Version Version = versionData.createVersionValidateDate();

        // WHEN
         assertThrows(ValidateDateException.class, () -> {
            versionUseCase.addVersion(Version);
        });
         //THEN
        verify(versionPersistencePort, never()).addVersion(any(Version.class));
    }
   /* @Test
    void testGetAllVersionByBootcamp() {
        // GIVEN
        List<Long> bootcampIds = List.of(1L);

        List<Version> versions = VersionData.createVersions();

        // WHEN
        when(versionPersistencePort
                .getAllVersionByBootcamp(bootcampIds, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_DATE))
                .thenReturn(versions);

        List<Version> actualVersions = versionUseCase
                .getAllVersionByBootcamp(bootcampIds, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_DATE);

        // Then
        assertEquals(versions, actualVersions);
        verify(versionPersistencePort)
                .getAllVersionByBootcamp(bootcampIds, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_DATE);
    }*/
}