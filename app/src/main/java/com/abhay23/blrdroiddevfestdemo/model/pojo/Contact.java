package com.abhay23.blrdroiddevfestdemo.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Contact implements Parcelable {
  private int id;
  @SerializedName("first_name") private String firstName;
  @SerializedName("last_name") private String lastName;
  @SerializedName("profile_pic") private String profilePic;
  @SerializedName("url") private String url;
  @SerializedName("phone_number") private String phoneNumber;
  @SerializedName("email") private String email;
  @SerializedName("favorite") private boolean favorite;
  @SerializedName("created_at") private Date createdAt;
  @SerializedName("updated_at") private Date updatedAt;

  private String fullName;

  public Contact() {
  }

  public Contact(int id, String firstName, String lastName, String profilePic, String url,
      String phoneNumber, String email, boolean favorite, Date createdAt, Date updatedAt) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.profilePic = profilePic;
    this.url = url;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.favorite = favorite;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Contact(String firstName, String lastName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFullName() {
    if (fullName != null) {
      return fullName;
    }

    String fullName = "";
    if (getFirstName() != null) {
      fullName = fullName.concat(getFirstName());
    }

    if (getLastName() != null) {
      fullName = fullName.concat(" " + getLastName());
    }
    this.fullName = fullName;
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean getFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFirstCharacter() {
    String fullName = getFullName();
    if (fullName != null && fullName.length() > 0) {
      return fullName.substring(0, 1);
    }
    return "#";
  }

  @Override public String toString() {
    return firstName;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.firstName);
    dest.writeString(this.lastName);
    dest.writeString(this.profilePic);
    dest.writeString(this.url);
    dest.writeString(this.phoneNumber);
    dest.writeString(this.email);
    dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
    dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
    dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
    dest.writeString(this.fullName);
  }

  protected Contact(Parcel in) {
    this.id = in.readInt();
    this.firstName = in.readString();
    this.lastName = in.readString();
    this.profilePic = in.readString();
    this.url = in.readString();
    this.phoneNumber = in.readString();
    this.email = in.readString();
    this.favorite = in.readByte() != 0;
    long tmpCreatedAt = in.readLong();
    this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
    long tmpUpdatedAt = in.readLong();
    this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
    this.fullName = in.readString();
  }

  public static final Creator<Contact> CREATOR = new Creator<Contact>() {
    @Override public Contact createFromParcel(Parcel source) {
      return new Contact(source);
    }

    @Override public Contact[] newArray(int size) {
      return new Contact[size];
    }
  };

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Contact)) return false;

    Contact contact = (Contact) o;

    if (id != contact.id) return false;
    if (favorite != contact.favorite) return false;
    if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) {
      return false;
    }
    if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) {
      return false;
    }
    if (profilePic != null ? !profilePic.equals(contact.profilePic) : contact.profilePic != null) {
      return false;
    }
    if (url != null ? !url.equals(contact.url) : contact.url != null) return false;
    if (phoneNumber != null ? !phoneNumber.equals(contact.phoneNumber)
        : contact.phoneNumber != null) {
      return false;
    }
    if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
    if (createdAt != null ? !createdAt.equals(contact.createdAt) : contact.createdAt != null) {
      return false;
    }
    if (updatedAt != null ? !updatedAt.equals(contact.updatedAt) : contact.updatedAt != null) {
      return false;
    }
    return fullName != null ? fullName.equals(contact.fullName) : contact.fullName == null;
  }

  @Override public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (profilePic != null ? profilePic.hashCode() : 0);
    result = 31 * result + (url != null ? url.hashCode() : 0);
    result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (favorite ? 1 : 0);
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
    return result;
  }
}