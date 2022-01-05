package com.overclock.overclock.service.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.AssemblyDAO;
import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.User;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.CommentService;
import com.overclock.overclock.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssemblyServiceImplTest {

    @InjectMocks
    private AssemblyService assemblyService = new AssemblyServiceImpl();
    @Mock
    private AssemblyDAO assemblyDAOMock;
    @Mock
    private UserService userServiceMock;
    @Mock
    private CommentService commentServiceMock;

    private Assembly testAssembly1;
    private Assembly testAssembly2;
    private List<Assembly> testAssemblies = new ArrayList<>();
    private User user1;
    private User user2;



    @Before
    public void init() {
       testAssembly1 =  new Assembly.Builder(BigInteger.valueOf(1), "Assembly1")
                .setRam(CreateUtilities.createRAM())
                .setGpu(CreateUtilities.createGpu())
                .setCpu(CreateUtilities.createCpu())
                .setMotherboard(CreateUtilities.createMotherboard())
                .setScore(BigDecimal.valueOf(3500))
                .setAuthor(BigInteger.valueOf(1))
                .setOverclock(BigInteger.valueOf(11))
                .setComments(CreateUtilities.createAssemblyWithFullInformation().getComments())
                .build();
       testAssembly2 =  new Assembly.Builder(BigInteger.valueOf(2), "Assembly2")
                .setRam(CreateUtilities.createRAM())
                .setGpu(CreateUtilities.createGpu())
                .setCpu(CreateUtilities.createCpu())
                .setMotherboard(CreateUtilities.createMotherboard())
                .setScore(BigDecimal.valueOf(0))
                .setAuthor(BigInteger.valueOf(2))
                .setOverclock(BigInteger.valueOf(1))
                .setComments(CreateUtilities.createAssemblyWithFullInformation().getComments())
                .build();
       user1 = new User.Builder()
                .setId(BigInteger.valueOf(1))
                .setUserName("user1")
                .build();
       user2 = new User.Builder()
                .setId(BigInteger.valueOf(2))
                .setUserName("user2")
                .build();
       testAssemblies.add(testAssembly1);
       testAssemblies.add(testAssembly2);

    }

    @Test
    public void getAssembliesByIdWithSomeCommentsValidId(){
        BigInteger limit = BigInteger.valueOf(2);
        when(assemblyDAOMock.getAssemblyById(BigInteger.valueOf(2))).thenReturn(testAssembly2);
        when(commentServiceMock.getLimitedListOfCommentsByAssemblyId(BigInteger.valueOf(2),
                limit)).thenReturn(testAssembly2.getComments());
        Assembly assembly = assemblyService.getAssemblyByIdWithSomeComments(BigInteger.valueOf(2), limit);
        Assertions.assertEquals(testAssembly2, assembly);
    }

    @Test
    public void getAssembliesByIdWithSomeCommentsInValidId(){
        BigInteger limit = BigInteger.valueOf(2);
        Assembly assembly1 = assemblyService.getAssemblyByIdWithSomeComments(BigInteger.valueOf(100), limit);
        Assembly assembly2 = assemblyService.getAssemblyByIdWithSomeComments(null, limit);
        Assertions.assertNull(assembly1);
        Assertions.assertNull(assembly2);
    }

    @Test
    public void searchValidNameOfUser(){
        when(userServiceMock.getWithMainInformation(testAssembly1.getAuthor())).thenReturn(user1);
        when(userServiceMock.getWithMainInformation(testAssembly2.getAuthor())).thenReturn(user2);
        when(assemblyDAOMock.getAllAssemblies()).thenReturn(testAssemblies);
        List<Assembly> assemblies = assemblyService.search("user1");
        List<Assembly> expected = new ArrayList<>();
        expected.add(testAssembly1);
        Assertions.assertEquals(expected, assemblies);
    }

    @Test
    public void searchValidNameOfAssembly(){
        when(userServiceMock.getWithMainInformation(testAssembly1.getAuthor())).thenReturn(user1);
        when(userServiceMock.getWithMainInformation(testAssembly2.getAuthor())).thenReturn(user2);
        when(assemblyDAOMock.getAllAssemblies()).thenReturn(testAssemblies);
        List<Assembly> assemblies = assemblyService.search("Assembly2");
        List<Assembly> expected = new ArrayList<>();
        expected.add(testAssembly2);
        Assertions.assertEquals(expected, assemblies);
    }

    @Test
    public void searchInvalidString(){
        when(userServiceMock.getWithMainInformation(testAssembly1.getAuthor())).thenReturn(user1);
        when(userServiceMock.getWithMainInformation(testAssembly2.getAuthor())).thenReturn(user2);
        when(assemblyDAOMock.getAllAssemblies()).thenReturn(testAssemblies);
        List<Assembly> assemblies = assemblyService.search("wrongName");
        List<Assembly> expected = new ArrayList<>();
        Assertions.assertEquals(expected, assemblies);
    }

    @Test
    public void searchNullString(){
        List<Assembly> assemblies = assemblyService.search(null);
        Assertions.assertNull(assemblies);
    }
}
