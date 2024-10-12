package com.example.BrowseTrackv2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkLog {
    private String id;
    private String logMessage;
}
