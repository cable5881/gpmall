import com.gpmall.search.SearchProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/11/21 17:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchProviderApplication.class)
public class ElasticsearchApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void createIndex2(){
        Book book = new Book();
        book.setId(1);
        book.setBookName("西游记");
        book.setAuthor( "吴承恩" );
        bookRepository.index( book );
    }

    @Test
    public void useFind() {
        List<Book> list = bookRepository.findByBookNameLike( "游" );
        for (Book book : list) {
            System.out.println(book);
        }

    }
}
