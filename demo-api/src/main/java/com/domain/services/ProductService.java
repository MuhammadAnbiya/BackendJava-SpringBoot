package com.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepo;

@Service // setiap class service harus didefinisikan seperti ini dulu
@Transactional // untuk untuk membantu business logic
public class ProductService {

    @Autowired // anotasi untuk injeksi dependensi 
    private ProductRepo productRepo; // jadi class ProductRepo di deklarasikan sebagai productRepo untuk memanggil logicnya

    public Product save(Product product){  // class untuk method create di controller yg memanggil method save dari productRepo
        return productRepo.save(product);
    }

    public Product findOne(Long id){ // class untuk method getProductByID di controller
        Optional <Product> product = productRepo.findById(id); //Optional untuk kondisi jika ada dan jika tidak ada
        if (!product.isPresent()){ // kalau tidak ada maka akan return null (kosong)
            return null;
        }
        return product.get(); // kalau ada maka akan memunculkan product dari Id nya
    }

    public Iterable<Product> findAll(){ // class untuk method getAllProduct, karena dia mencari semua maka pake Iterasi (perulangan)
        return productRepo.findAll();
    }
    
    public void removeOne(Long id){ // dia void karena untuk method delete
        productRepo.deleteById(id); // yang tidak akan mengembalikan apa apa jadi tidak pake return juga
    }
    
    public void removeAll(){ // class untuk menghapus product secara keseluruhan
        productRepo.deleteAll();
    }

    public void addSupplier(Supplier supplier, Long productId){ // mencari product dari productId nya
        Product product = findOne(productId); 
        if(product==null){
            throw new RuntimeException("Product with ID: "+productId+ " not found"); // kalau gagal dikirim error pake throw
        }
        product.getSuppliers().add(supplier); // kalau ngga di tambahin supplier nya
        save(product);
    }

    public Product findByProductName(String name){     // class untuk mencari product berdasarkan namanya
        return productRepo.findProductByName(name);
    }

    public List<Product> findProductByNameLike(String name){
        return productRepo.findProductByNameLike("%"+name+"%");
    }
}