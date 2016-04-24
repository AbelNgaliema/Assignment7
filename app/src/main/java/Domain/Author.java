package Domain;

import java.io.Serializable;

/**
 * Created by AbelN on 15/04/2016.
 */
public class Author implements Serializable, AuthorInterface {

    private String name;
    private String surname;
    private long id;

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    @Override
    public long getId() {

        return id;
    }

    public Author (Builder builder)
    {
        this.name = builder.name;
        this.surname = builder.surname;
        this.id = builder.id;
    }

    public static class Builder{

        private String name;
        private String surname;
        private long id;
        public Builder(long value)
        {
            this.id = value;
        }
        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder surname(String value){
            this.surname = value;
            return this;
        }

        public Builder copy(Author value){
            this.name = value.name;
            this.surname = value.surname;
            this.id = value.id;
            return this;
        }

        public Author build(){
            return new Author(this);
        }

    }
}

