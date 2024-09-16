package com.luispintodesa.bartender.models.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WhatCanIMakeForm {

  @Size(min = 2, max = 15)
  @NotNull
  private String cocktailName;

}
