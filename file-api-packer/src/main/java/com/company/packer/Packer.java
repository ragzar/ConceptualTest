package com.company.packer;

import java.util.List;
import java.util.stream.Collectors;

import com.company.exception.APIException;
import com.company.packer.model.Package;
import com.company.packer.service.PackageService;

public class Packer {

    private static PackageService service = new PackageService();

    public static String pack(String filePath) throws APIException {

	List<Package> packages = service.loadPackageFile(filePath);

	return packages.stream().map(p -> service.findBestItemCombination(p))
		.collect(Collectors.joining("\n"));
    }
    
}
