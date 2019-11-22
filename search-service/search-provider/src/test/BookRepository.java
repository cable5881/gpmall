import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/11/21 17:43
 **/
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

    List<Book> findByBookNameLike(String bookName);

}
