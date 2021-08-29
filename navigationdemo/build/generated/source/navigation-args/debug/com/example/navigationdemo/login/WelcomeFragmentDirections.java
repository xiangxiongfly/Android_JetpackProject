package com.example.navigationdemo.login;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.navigationdemo.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class WelcomeFragmentDirections {
  private WelcomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionToLogin() {
    return new ActionOnlyNavDirections(R.id.action_to_login);
  }

  @NonNull
  public static ActionToRegister actionToRegister() {
    return new ActionToRegister();
  }

  public static class ActionToRegister implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionToRegister() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionToRegister setData(@NonNull String data) {
      if (data == null) {
        throw new IllegalArgumentException("Argument \"data\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("data", data);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("data")) {
        String data = (String) arguments.get("data");
        __result.putString("data", data);
      } else {
        __result.putString("data", "default");
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_to_register;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getData() {
      return (String) arguments.get("data");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionToRegister that = (ActionToRegister) object;
      if (arguments.containsKey("data") != that.arguments.containsKey("data")) {
        return false;
      }
      if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getData() != null ? getData().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionToRegister(actionId=" + getActionId() + "){"
          + "data=" + getData()
          + "}";
    }
  }
}
