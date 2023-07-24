package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    TrelloClient trelloClient;

    @Mock
    RestTemplate restTemplate;

    @Mock
    TrelloConfig trelloConfig;

    @Test
    void shouldFetchTrelloBoards() throws URISyntaxException {
        //given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUsername()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "Kodilla", new ArrayList<>());

        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDto> fetchTrelloBoard = trelloClient.getTrelloBoards();

        //then
        assertEquals(1, fetchTrelloBoard.size());
        assertEquals("test_id", fetchTrelloBoard.get(0).getId());
        assertEquals("Kodilla", fetchTrelloBoard.get(0).getName());
        assertEquals(new ArrayList<>(), fetchTrelloBoard.get(0).getLists());
    }

    @Test
    void shouldCreateCard() throws URISyntaxException {
        //given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");


        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "Test Description", "top", "test_id");

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard("1", "Test task", "http://test.com");

        when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //when
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

//    @Test
//    void shouldReturnEmptyList() throws URISyntaxException {
//        //given
//        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
//        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
//        when(trelloConfig.getTrelloToken()).thenReturn("test");
//        when(trelloConfig.getTrelloUsername()).thenReturn("test");
//
//        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");
//
//        //when
//        when(restTemplate.getForObject(uri, TrelloCardDto[].class)).thenReturn(null);
//        List<TrelloBoardDto> emptyList = trelloClient.getTrelloBoards();
//
//        //then
//        assertEquals(0, emptyList.size());
//    }

}