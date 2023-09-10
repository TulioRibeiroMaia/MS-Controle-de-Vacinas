package mscitizen.service;

import mscitizen.entity.Citizen;
import mscitizen.repository.CitizenRepository;
import mscitizen.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CitizenServiceTest {

    @InjectMocks
    private CitizenService citizenService;

    @Mock
    private CitizenRepository citizenRepository;

    @Test
    void deleteCitizen() throws Exception {
    }
}
