package com.vti.dulichviet_team_1.modal.dto;


import com.vti.dulichviet_team_1.modal.entity.TourStatus;
import com.vti.dulichviet_team_1.modal.entity.Transport;
import com.vti.dulichviet_team_1.modal.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class TourRequestCreateDto {

    private String title;


    private Type type;


    private String depart;


    private String arrival;

    private int duration;

    private Transport transport;

    private String content;

    private String image;

    private int price;

    private TourStatus status;


}
