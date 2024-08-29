package com.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepo;

@Service // setiap class service harus didefinisikan seperti ini dulu
@Transactional // untuk untuk membantu business logic
public class ProductService {

    @Autowired // anotasi untuk injeksi dependensi 
    private ProductRepo productRepo; // jadi class ProductRepo di deklarasikan sebagai productRepo untuk memanggil logicnya

    @Autowired
    private SupplierService supplierService; // SupplierService d inject di Product service untuk memanggil method findOne

    public Product save(Product product){  // class untuk method create di controller yg memanggil method save dari productRepo
        return productRepo.save(product);
    }

    // class untuk method getProductByID di controller
    public Product findOne(Long id){ 
        Optional <Product> product = productRepo.findById(id); //Optional untuk kondisi jika ada dan jika tidak ada
        if (!product.isPresent()){ // kalau tidak ada maka akan return null (kosong)
            return null;
        }
        return product.get(); // kalau ada maka akan memunculkan product dari Id nya
    }

    // class untuk method getAllProduct, karena dia mencari semua maka pake Iterasi (perulangan)
    public Iterable<Product> findAll(){ 
        return productRepo.findAll();
    }
    
    // class untuk method delete, dia void karena delete tidak akan mengembalikan apa apa
    public void removeOne(Long id){ 
        productRepo.deleteById(id); // karean tidak akan mengembalikan apa apa jadi tidak pake return juga
    }
    
    // class untuk menghapus product secara keseluruhan
    public void removeAll(){ 
        productRepo.deleteAll();
    }

    // class untuk mencari product dari productId nya
    public void addSupplier(Supplier supplier, Long productId){ 
        Product product = findOne(productId); 
        if(product==null){
            throw new RuntimeException("Product with ID: "+productId+ " not found"); // kalau product gaada akan runtimeException
        }
        product.getSuppliers().add(supplier); // kalau product ada akan di tambahin supplier nya
        save(product);
    }

    // class untuk mencari product berdasarkan namanya
    public Product findByProductName(String name){     
        return productRepo.findProductByName(name);
    }

    // class untuk mencari product product tapi tidak harus lengkap (NameLike)
    public List<Product> findProductByNameLike(String name){ 
        return productRepo.findProductByNameLike("%"+name+"%");
    }

    // class untuk mencari product dari categorynya
    public List<Product> findProductByCategory(Long categoryId){ 
        return productRepo.findProductByCategory(categoryId);
    }

    // class untuk mencari product dari suppliernya
    public List<Product> findBySupplier(Long supplierId){ 
        Supplier supplier = supplierService.findOne(supplierId);
        if (supplier == null) {
            return new ArrayList<Product>(); // jika data supplier tidak ditemukan maka akan return array kosong saja
        }
        return productRepo.findProductBySupplier(supplier);
    }
}