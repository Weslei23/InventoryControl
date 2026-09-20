package com.wsdev.simplestock.domain.product;

import com.wsdev.simplestock.common.exceptions.RecordNotFoundException;
import com.wsdev.simplestock.domain.category.dto.request.CategoryRequestDTO;
import com.wsdev.simplestock.domain.category.dto.response.CategoryResponseDTO;
import com.wsdev.simplestock.domain.product.dto.request.ProductRequestDTO;
import com.wsdev.simplestock.domain.product.dto.response.ProductResponseDTO;
import com.wsdev.simplestock.domain.category.mapper.CategoryMapper;
import com.wsdev.simplestock.domain.product.mapper.ProductMapper;
import com.wsdev.simplestock.domain.category.model.Category;
import com.wsdev.simplestock.domain.product.model.Product;
import com.wsdev.simplestock.domain.category.CategoryRepository;
import com.wsdev.simplestock.common.utilities.Validator;
import com.wsdev.simplestock.domain.supplier.SupplierRepository;
import com.wsdev.simplestock.domain.supplier.model.Supplier;
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

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     *
     * @return
     */
    public List<ProductResponseDTO> getProducts()
    {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for( Product product : productRepository.findAll() )
        {
            productResponseDTOS.add( ProductMapper.entityToDto( product ) );
        }
        return productResponseDTOS;
    }

    /**
     *
     * @param id
     * @return
     */
    public ProductResponseDTO getProductById( Long id )
    {
        Product product = productRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        return ProductMapper.entityToDto( product );
    }

    /**
     *
     * @param categoryName
     * @return
     */
    public List<ProductResponseDTO> getProductsByCategoryName( String categoryName )
    {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        Category category = categoryRepository.getCategoryByName( categoryName );

        for( Product product : productRepository.getProductsByCategory( category ) )
        {
            productResponseDTOS.add( ProductMapper.entityToDto( product ) );
        }

        return productResponseDTOS;
    }

    /**
     *
     * @param categoryRequestDTO
     * @return
     */
    public ProductResponseDTO getProductByCategory( CategoryRequestDTO categoryRequestDTO )
    {
        Product product = productRepository.getProductByCategory( CategoryMapper.dtoToEntity( categoryRequestDTO ) );

        return ProductMapper.entityToDto( product );
    }

    /**
     * addProduct
     * @param productRequestDTO
     */
    public void addProduct( ProductRequestDTO productRequestDTO ) throws Exception
    {
        Validator.requiredNonNull( productRequestDTO, new IllegalArgumentException( "Argument 'productDTO' must not be null" ) );

        Category category = categoryRepository.findById( productRequestDTO.getCategoryId() )
                .orElseThrow( () -> new RecordNotFoundException( productRequestDTO.getCategoryId() ) );

        Supplier supplier = supplierRepository.findById( productRequestDTO.getSupplierId() )
                .orElseThrow( () -> new RecordNotFoundException( productRequestDTO.getSupplierId() ) );

        Product product = ProductMapper.dtoToEntity( productRequestDTO );

        product.setCategory( category );
        product.setSupplier( supplier );

        productRepository.save( product );
    }

    /**
     *
     * @param id
     * @param productRequestDTO
     */
    public void updateProduct( Long id, ProductRequestDTO productRequestDTO )
    {
        Product product = productRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

        Category category = categoryRepository.findById( productRequestDTO.getCategoryId() )
                .orElseThrow( () -> new RecordNotFoundException( productRequestDTO.getCategoryId() ) );

        Supplier supplier = supplierRepository.findById( productRequestDTO.getSupplierId() )
                .orElseThrow( () -> new RecordNotFoundException( productRequestDTO.getSupplierId() ) );


        product.setName( productRequestDTO.getName() );
        product.setDescription( productRequestDTO.getDescription() );
        product.setQuantity( productRequestDTO.getQuantity() );
        product.setImage( productRequestDTO.getImage() );
        product.setMaximumQuantity( productRequestDTO.getMaximumQuantity() );
        product.setMinimumQuantity( productRequestDTO.getMinimumQuantity() );
        product.setCategory( category );
        product.setPrice( productRequestDTO.getPrice() );
        product.setSupplier( supplier );

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