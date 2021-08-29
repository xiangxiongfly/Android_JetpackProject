package com.example.navigationdemo.login;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class RegisterFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private RegisterFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private RegisterFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static RegisterFragmentArgs fromBundle(@NonNull Bundle bundle) {
    RegisterFragmentArgs __result = new RegisterFragmentArgs();
    bundle.setClassLoader(RegisterFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("data")) {
      String data;
      data = bundle.getString("data");
      if (data == null) {
        throw new IllegalArgumentException("Argument \"data\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("data", data);
    } else {
      __result.arguments.put("data", "default");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getData() {
    return (String) arguments.get("data");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    RegisterFragmentArgs that = (RegisterFragmentArgs) object;
    if (arguments.containsKey("data") != that.arguments.containsKey("data")) {
      return false;
    }
    if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getData() != null ? getData().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "RegisterFragmentArgs{"
        + "data=" + getData()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(RegisterFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public RegisterFragmentArgs build() {
      RegisterFragmentArgs result = new RegisterFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setData(@NonNull String data) {
      if (data == null) {
        throw new IllegalArgumentException("Argument \"data\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("data", data);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getData() {
      return (String) arguments.get("data");
    }
  }
}
