package com.wugui.datax.admin.config;

import com.google.common.collect.Multimap;
import io.swagger.models.*;
import io.swagger.models.Contact;
import io.swagger.models.Operation;
import io.swagger.models.Tag;
import io.swagger.models.parameters.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import springfox.documentation.service.*;
import springfox.documentation.swagger2.mappers.*;

import java.util.*;

import static com.google.common.collect.Maps.newTreeMap;

/**
 * application configuration
 */
@Component(value = "ServiceModelToSwagger2Mapper")
@Primary
@ConditionalOnWebApplication
public class ServiceModelToSwagger2MapperImpl extends ServiceModelToSwagger2Mapper {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ParameterMapper parameterMapper;
    @Autowired
    private SecurityMapper securityMapper;
    @Autowired
    private LicenseMapper licenseMapper;
    @Autowired
    private VendorExtensionsMapper vendorExtensionsMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Swagger mapDocumentation(Documentation from) {

        if (from == null) {
            return null;
        }

        Swagger swagger = new Swagger();

        swagger.setVendorExtensions(vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        swagger.setSchemes(mapSchemes(from.getSchemes()));
        swagger.setPaths(mapApiListings(from.getApiListings()));
        swagger.setHost(from.getHost());
        swagger.setDefinitions(modelsFromApiListings( from.getApiListings() ) );
        swagger.setSecurityDefinitions(securityMapper.toSecuritySchemeDefinitions(from.getResourceListing()));
        ApiInfo info = fromResourceListingInfo(from);
        if (info != null) {
            swagger.setInfo(mapApiInfo(info));
        }
        swagger.setBasePath(from.getBasePath());
        swagger.setTags(tagSetToTagList(from.getTags()));
        List<String> list2 = from.getConsumes();
        if (list2 != null) {
            swagger.setConsumes(new ArrayList<String>(list2));
        } else {
            swagger.setConsumes(null);
        }
        List<String> list3 = from.getProduces();
        if (list3 != null) {
            swagger.setProduces(new ArrayList<String>(list3));
        } else {
            swagger.setProduces(null);
        }

        return swagger;
    }


    @Override
    protected Info mapApiInfo(ApiInfo from) {

        if (from == null) {
            return null;
        }

        Info info = new Info();

        info.setLicense(licenseMapper.apiInfoToLicense(from));
        info.setVendorExtensions(vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        info.setTermsOfService(from.getTermsOfServiceUrl());
        info.setContact(map(from.getContact()));
        info.setDescription(from.getDescription());
        info.setVersion(from.getVersion());
        info.setTitle(from.getTitle());

        return info;
    }

    @Override
    protected Contact map(springfox.documentation.service.Contact from) {

        if (from == null) {
            return null;
        }

        Contact contact = new Contact();

        contact.setName(from.getName());
        contact.setUrl(from.getUrl());
        contact.setEmail(from.getEmail());

        return contact;
    }


    @Override
    protected Operation mapOperation(springfox.documentation.service.Operation from) {

        if (from == null) {
            return null;
        }

        Locale locale = LocaleContextHolder.getLocale();

        Operation operation = new Operation();

        operation.setSecurity(mapAuthorizations(from.getSecurityReferences()));
        operation.setVendorExtensions(vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        operation.setDescription(messageSource.getMessage(from.getNotes(), null, from.getNotes(), locale));
        operation.setOperationId(from.getUniqueId());
        operation.setResponses(mapResponseMessages(from.getResponseMessages()));
        operation.setSchemes(stringSetToSchemeList(from.getProtocol()));
        Set<String> tagsSet = new HashSet<>(1);

        if(from.getTags() != null && from.getTags().size() > 0){

            List<String> list = new ArrayList<String>(tagsSet.size());

            Iterator<String> it = from.getTags().iterator();
            while(it.hasNext()){
               String tag = it.next();
               list.add(
                   StringUtils.isNotBlank(tag) ? messageSource.getMessage(tag, null, tag, locale) : " ");
            }

            operation.setTags(list);
        }else {
            operation.setTags(null);
        }

        operation.setSummary(from.getSummary());
        Set<String> set1 = from.getConsumes();
        if (set1 != null) {
            operation.setConsumes(new ArrayList<String>(set1));
        } else {
            operation.setConsumes(null);
        }

        Set<String> set2 = from.getProduces();
        if (set2 != null) {
            operation.setProduces(new ArrayList<String>(set2));
        } else {
            operation.setProduces(null);
        }


        operation.setParameters(parameterListToParameterList(from.getParameters()));
        if (from.getDeprecated() != null) {
            operation.setDeprecated(Boolean.parseBoolean(from.getDeprecated()));
        }

        return operation;
    }

    @Override
    protected Tag mapTag(springfox.documentation.service.Tag from) {

        if (from == null) {
            return null;
        }

        Locale locale = LocaleContextHolder.getLocale();

        Tag tag = new Tag();

        tag.setVendorExtensions(vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        tag.setName(messageSource.getMessage(from.getName(), null, from.getName(), locale));
        tag.setDescription(from.getDescription());

        return tag;
    }


    private ApiInfo fromResourceListingInfo(Documentation documentation) {

        if (documentation == null) {
            return null;
        }
        ResourceListing resourceListing = documentation.getResourceListing();
        if (resourceListing == null) {
            return null;
        }
        ApiInfo info = resourceListing.getInfo();
        if (info == null) {
            return null;
        }
        return info;
    }

    protected List<Tag> tagSetToTagList(Set<springfox.documentation.service.Tag> set) {

        if (set == null) {
            return null;
        }

        List<Tag> list = new ArrayList<Tag>(set.size());
        for (springfox.documentation.service.Tag tag : set) {
            list.add(mapTag(tag));
        }

        return list;
    }

    protected List<Scheme> stringSetToSchemeList(Set<String> set) {
        if (set == null) {
            return null;
        }

        List<Scheme> list = new ArrayList<Scheme>(set.size());
        for (String string : set) {
            list.add(Enum.valueOf(Scheme.class, string));
        }

        return list;
    }

    protected List<Parameter> parameterListToParameterList(List<springfox.documentation.service.Parameter> list) {
        if (list == null) {
            return null;
        }

        List<Parameter> list1 = new ArrayList<Parameter>(list.size());

        Locale locale = LocaleContextHolder.getLocale();

        for (springfox.documentation.service.Parameter param : list) {
            String description = messageSource.getMessage(param.getDescription(), null, param.getDescription(), locale);

            springfox.documentation.service.Parameter parameter = new springfox.documentation.service.Parameter(param.getName(),description,param.getDefaultValue(),param.isRequired(),param.isAllowMultiple(),param.isAllowEmptyValue(),param.getModelRef(),param.getType(),param.getAllowableValues(),param.getParamType(),param.getParamAccess(),param.isHidden(),param.getPattern(),param.getCollectionFormat(),param.getOrder(),param.getScalarExample(),param.getExamples() ,param.getVendorExtentions());
            list1.add(parameterMapper.mapParameter(parameter));
        }

        return list1;
    }


    Map<String, Model> modelsFromApiListings(Multimap<String, ApiListing> apiListings) {
        Map<String, springfox.documentation.schema.Model> definitions = newTreeMap();
        for (ApiListing each : apiListings.values()) {
            definitions.putAll(each.getModels());
        }
        return modelMapper.mapModels(definitions);
    }

}
