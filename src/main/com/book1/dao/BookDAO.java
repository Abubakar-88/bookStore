package com.book1.dao;

import com.book1.entity.Book;

import java.util.Date;
import java.util.List;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book>  {

public BookDAO(){

}

@Override
    public Book create(Book book){
    book.setLastUpdateTime(new Date());
    return super.create(book);
}

@Override
    public Book update(Book book){
    book.setLastUpdateTime(new Date());
    return super.update(book);
}

@Override
    public Book get(Object bookId){
    return super.find(Book.class, bookId);

}

    @Override
    public void delete(Object bookId){
     super.delete(Book.class, bookId);
}

@Override
    public List<Book> listAll (){
    return super.findWithNamedQuery("Book.findAll");
}

 public Book findByTitle(String title){
    List<Book> result = super.findWithNamedQuery("Book.findByTitle", "title", title);
    if(!result.isEmpty()){
        return result.get(0);
    }
    return null;
 }


    @Override
    public long count() {
        return super.countWithNamedQuery("Book.countAll");
    }
    public long countByCategory(int categoryId) {
        return super.countWithNamedQuery("Book.countByCategory", "catId", categoryId);
    }
}
