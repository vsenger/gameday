package org.gameday.service;

import org.gameday.model.Team;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

@ApplicationScoped
public class TeamService {

    @Inject
    DynamoDbClient dynamoDB;

    public List<Team> findAll() {
        List<Team> teams = dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Team::from)
                .collect(Collectors.toList());
        return teams;
    }
    public List<Team> findByDescription(String description) {
        List<Team> teams = dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Team::from)
                .filter(team -> team.getDescription().equals(description))
                .collect(Collectors.toList());
        return teams;
    }

    public Team findByID(String ID) {
        return  Team.from(dynamoDB.getItem(getRequest(ID)).item());
    }

    public void addEntry(Team team) {
        dynamoDB.putItem(putRequest(team));
    }
    public static final String TEAM_ID_COL = "teamID";
    public static final String ENTRY_DESCRIPTION_COL = "description";

    public String getTableName() {
        return "gameday_teams";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(TEAM_ID_COL, ENTRY_DESCRIPTION_COL).build();
    }

    protected PutItemRequest putRequest(Team team) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(TEAM_ID_COL, AttributeValue.builder().s(team.getTeamID()).build());
        item.put(ENTRY_DESCRIPTION_COL, AttributeValue.builder().s(team.getDescription()).build());
        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(TEAM_ID_COL, AttributeValue.builder().s(name).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(TEAM_ID_COL, ENTRY_DESCRIPTION_COL)
                .build();
    }

}
