package com.project.monica.snobsinenobilitate.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by monica on 25/02/2015.
 */
public class BusProvider {

  private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

  private BusProvider()
  {

  }

  public static Bus getInstance()
  {
    return BUS;
  }
}
