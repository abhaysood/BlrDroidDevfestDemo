package com.abhay23.blrdroiddevfestdemo.contacts_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.abhay23.blrdroiddevfestdemo.R;
import com.abhay23.blrdroiddevfestdemo.model.pojo.Contact;
import com.bumptech.glide.Glide;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>
    implements FastScrollRecyclerView.SectionedAdapter {

  private final static String BASE_IMAGE_URL = "https://sonorous-seat-708.firebaseio.com/";
  private List<Contact> contacts;

  public ContactsAdapter() {
    contacts = new ArrayList<>();
  }

  @Override public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
    return new ContactsViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ContactsViewHolder holder, int position) {
    holder.bind(position);
  }

  @Override public int getItemCount() {
    return contacts.size();
  }

  @NonNull @Override public String getSectionName(int position) {
    return contacts.get(position).getFirstCharacter().toUpperCase();
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
    notifyDataSetChanged();
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public class ContactsViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.contact_img) ImageView contactImageView;
    @Bind(R.id.contact_name) TextView contactTextView;

    public ContactsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bind(int position) {
      Contact contact = contacts.get(position);
      contactTextView.setText(contact.getFullName());
      Glide.with(contactImageView.getContext())
          .load(BASE_IMAGE_URL + contact.getProfilePic())
          .placeholder(R.drawable.placeholder)
          .centerCrop()
          .into(contactImageView);
    }
  }
}
