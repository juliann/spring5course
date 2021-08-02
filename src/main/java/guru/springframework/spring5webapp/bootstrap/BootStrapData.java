package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(
      AuthorRepository authorRepository,
      BookRepository bookRepository,
      PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Publisher publisher = new Publisher();
    publisher.setCity("london");
    publisher.setName("sfg publishing");
    publisher.setState("city of london");

    publisherRepository.save(publisher);

    System.out.println("publisher count: " + publisherRepository.count());

    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("ddd", "1234");
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);
    ddd.setPublisher(publisher);
    publisher.getBooks().add(ddd);

    authorRepository.save(eric);
    bookRepository.save(ddd);
    publisherRepository.save(publisher);

    Author rod = new Author("rod", "johnsen");
    Book noEJB = new Book("noEJB", "1235");
    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);
    noEJB.setPublisher(publisher);
    publisher.getBooks().add(noEJB);

    authorRepository.save(rod);
    bookRepository.save(noEJB);
    publisherRepository.save(publisher);

    System.out.println("number of books:" + bookRepository.count());
    System.out.println("publisher bookds count: " + publisher.getBooks().size());
  }
}
