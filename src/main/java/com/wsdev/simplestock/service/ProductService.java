package com.wsdev.simplestock.service;

import com.wsdev.simplestock.Exception.RecordNotFoundException;
import com.wsdev.simplestock.dto.CategoryDTO;
import com.wsdev.simplestock.dto.ProductDTO;
import com.wsdev.simplestock.dto.mapper.CategoryMapper;
import com.wsdev.simplestock.dto.mapper.ProductMapper;
import com.wsdev.simplestock.entity.Category;
import com.wsdev.simplestock.entity.Product;
import com.wsdev.simplestock.repository.CategoryRepository;
import com.wsdev.simplestock.repository.ProductRepository;
import com.wsdev.simplestock.utilities.Validator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     *
     * @return
     */
    public List<ProductDTO> getProducts()
    {
        List<ProductDTO> productDTOS = new ArrayList<>();

        for( Product product : productRepository.findAll() )
        {
            productDTOS.add( ProductMapper.entityToDto( product ) );
        }
        return productDTOS;
    }

    /**
     *
     * @param id
     * @return
     */
    public ProductDTO getProductById( Long id )
    {
        Product product = productRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        return ProductMapper.entityToDto( product );
    }

    /**
     *
     * @param categoryName
     * @return
     */
    public List<ProductDTO> getProductsByCategoryName( String categoryName )
    {
        List<ProductDTO> productDTOS = new ArrayList<>();

        Category category = categoryRepository.getCategoryByName( categoryName );

        for( Product product : productRepository.getProductsByCategory( category ) )
        {
            productDTOS.add( ProductMapper.entityToDto( product ) );
        }

        return productDTOS;
    }

    /**
     *
     * @param categoryDTO
     * @return
     */
    public ProductDTO getProductByCategory( CategoryDTO categoryDTO )
    {
        Product product = productRepository.getProductByCategory( CategoryMapper.dtoToEntity( categoryDTO ) );

        return ProductMapper.entityToDto( product );
    }

    /**
     *
     * @param productDTO
     */
    public void addProduct( ProductDTO productDTO ) throws Exception
    {
        Validator.requiredNonNull( productDTO, new IllegalArgumentException( "Argument 'productDTO' must not be null" ) );
        productRepository.save( ProductMapper.dtoToEntity( productDTO ) );
    }

    /**
     *
     * @param id
     * @param productDTO
     */
    public void updateProduct( Long id, ProductDTO productDTO )
    {
        Product product = productRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );
        Category category = categoryRepository.findById( productDTO.getCategoryDTO().getId() ).orElseThrow( () -> new RecordNotFoundException( id ) );

        if ( category.getName().equalsIgnoreCase( productDTO.getCategoryDTO().getName() )  )
        {
            throw new IllegalArgumentException( "Category name already exists" );
        }

        product.setName( productDTO.getName() );
        product.setDescription( productDTO.getDescription() );
        product.setQuantity( productDTO.getQuantity() );
        product.setImage( productDTO.getImage() );
        product.setMaximumQuantity( productDTO.getMaximumQuantity() );
        product.setMinimumQuantity( productDTO.getMinimumQuantity() );
        product.setCategory( category );
        product.setPrice( productDTO.getPrice() );

        productRepository.save( product );
    }

    /**
     *
     * @param id
     */
    public void deleteProduct( Long id )
    {
        Product product = productRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        productRepository.delete( product );
    }


    public byte[] downloadSheet() throws Exception
    {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet( "Products" );

        String[] coluns = { "Nome", "Descrição", "Quantidade Atual", "Quantidade Mínima", "Quantidade Máxima", "Categoria", "Preço", "Fornecedor" };

        HSSFRow headerRow = sheet.createRow( 0 );

        for ( int i = 0; i < coluns.length; i++ )
        {
            HSSFCell cell = headerRow.createCell( i );
            cell.setCellValue( coluns[i] );
        }

        int rowIdx = 1;
        for(  Product product : productRepository.findAll() )
        {
            HSSFRow row = sheet.createRow( rowIdx++ );

            row.createCell( 0 ).setCellValue( product.getName() );
            row.createCell( 1 ).setCellValue( product.getDescription() );
            row.createCell( 2 ).setCellValue( product.getQuantity() );
            row.createCell( 3 ).setCellValue( product.getMinimumQuantity() );
            row.createCell( 4 ).setCellValue( product.getMaximumQuantity() );
            row.createCell( 5 ).setCellValue( product.getCategory().getName() );
            row.createCell( 6 ).setCellValue( product.getPrice().toString() );
            row.createCell( 7 ).setCellValue( product.getSupplier().getName() );
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }
}